Please find enclosed my submission for the BBVA test.

There:
5 model classes - lightweight and not coupled to anything else
2 controller classes - contain the problem domain logic

4 test classes spanning across model and controller

To see the prototytpe in action, please run the MarketPriceReporterTest class
I have also created JUnit tests for the main classes where there was some non-trivial logic.

I have deliberately ommitted the use of any logging such as log4j as logging needs to be done very carefully in
low latency systems. One pattern to solve this involves creating a lambda-enabled wrapper class around the log4j
logger:

logger.debug("My method took " + evalMethodTime() + " secs");
the above line is dangerous as the evalMethodTime() will run whether we are in debug mode or not. A better way is

logger.debug( () -> "My method took " + evalMethodTime() + " secs");
the above method passes a function, not a string and hence we can engineer it so that the function is only called
if we are in debug mode!

My Assumptions:
- We are not calculating TRADED VWAP, we are calculating OPEN VWAP (i.e. the figure is based on the open interest in the order book).

- Assume that 'market' orders (i.e. orders requesting to trade immediately at 'traded' VWAP) are satisfied immediately and do not
stay on the order book (thus we do not receive notification about new 'market' orders)


