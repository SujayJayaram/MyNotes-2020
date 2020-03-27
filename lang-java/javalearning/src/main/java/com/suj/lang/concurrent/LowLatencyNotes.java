package com.suj.lang.concurrent;

/**
 * Created by sujayjayaram on 22/01/2016.
 *
 * - MAKE VARIABLES FINAL WHERE POSSIBLE
 * - Use a CompletionService object so that you can use the Futures as they complete rather than
 * the random order of calling get() on a list of Futures submitted to an ExecutorService
 * - Use Command Query Responsibility Separation (CQRS) Event Sourcing to write state to flat file
 * - CompareAndSet and LockStriping will reduce lock contention
 * - Synchronise all "Check then act" sequences
 * - Use atomic variables and non blocking synchronization where possible. (They are immune  to  deadlock  and  other  liveness  problems)
 */
public class LowLatencyNotes {
}
