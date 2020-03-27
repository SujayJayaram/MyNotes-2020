package com.suj.lang.concurrent.completablefuture;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by sujayjayaram on 28/01/2016.
 * Different ways of rendering an html page with a number of image tags inside it
 */
public class PageRenderer {

    private ExecutorService executor;

    // Using a single Future to wait for all images to download.
    // This is bad as we cannot render anything till all images
    // have downloaded!
    public void renderPageWithFutureClass(CharSequence source) {
        List<ImageInfo> info = scanForImageInfo(source);

        // create Callable representing download of all images
        final Callable<List<ImageData>> task = () -> info.stream()
                                                        .map(imageInfo -> imageInfo.downloadImage())
                                                        .collect(Collectors.toList()); // Collect as a new List<ImageData>

        // Submit download task to the executor
        // ONE SINGLE FUTURE will download them all
        Future<List<ImageData>> images = executor.submit(task);

        renderText(source);
        try {
            // get all downloaded images (blocking until all are available)
            final List<ImageData> imageDatas = images.get(); // *** BLOCKS UNTIL ALL IMAGES DOWNLOADED - NOT GOOD!!! ***

            // render images
            imageDatas.forEach(this::renderImage);

            // Could write:
            // imageDatas.forEach(imageData -> renderImage(imageData));

        } catch (InterruptedException e) {
            // Re-assert the thread’s interrupted status
            Thread.currentThread().interrupt();
            // We don’t need the result, so cancel the task too
            images.cancel(true);

        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause()); }
    }

    public void renderPageWithCompletionService(CharSequence source) throws Exception {
        List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService =
                new ExecutorCompletionService<>(executor);

        // Submit each download task to the completion service
        // i.e. create a LIST OF FUTURES - this is good as now we
        // can grab then off the CompletionService as they finish - see below.

        // We can't write
        //info.forEach(imageInfo ->
        //        completionService.submit(imageInfo.downloadImage()));
        // as we are then NOT supplying a lambda to the submit() method.
        //
        // Could write:
        // info.forEach(imageInfo ->
        //        completionService.submit( () -> imageInfo.downloadImage() ) );
        //
        info.forEach(imageInfo ->
                completionService.submit(imageInfo::downloadImage));

        renderText(source);

        // retrieve each RunnableFuture as it becomes
        // available (and when we are ready to process it).
        for (int t = 0; t < info.size(); t++) {
            Future<ImageData> imageFuture = completionService.take();
            renderImage(imageFuture.get());
        }
    }

    public void renderPageWithCompletableFuture(CharSequence source) {

        // Each item has a CompletableFuture created for it that then renders
        // independently after the download. One CompletableFuture per image.
        List<ImageInfo> info = scanForImageInfo(source);
        info.forEach(imageInfo ->
                CompletableFuture
                        .supplyAsync(imageInfo::downloadImage, executor)
                        .thenAccept(this::renderImage)); // Completion callback cf RxJava

        renderText(source);
    }

    // Similar to above but here we then wait on all of them to finish
    // then reder a completion icon.
    public void renderPageUsingJoinedCompletableFutures(CharSequence source) {
        List<ImageInfo> info = scanForImageInfo(source);
        CompletableFuture[] cfs = info.stream()
                                        .map(imageInfo ->
                                                CompletableFuture.runAsync(
                                                        () -> renderImage(imageInfo.downloadImage()), executor))
                                        .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).join(); // wait till they are all done
        renderImage(createDoneIcon());
    }

    private ImageData createDoneIcon() {
        return null;
    }

    private void renderText(CharSequence source) {
    }

    private void renderImage(ImageData imageData) {
        // STUB
    }


    /** If the Throwable is an Error, throw it; if it is a
     *  RuntimeException return it, otherwise throw IllegalStateException
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {

        return null; // STUB
    }
}
