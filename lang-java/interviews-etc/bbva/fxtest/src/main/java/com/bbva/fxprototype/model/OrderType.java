package com.bbva.fxprototype.model;

/**
 * Indicates the type of the order.
 * Some venues will not allow 'amend' types as this would allow greedy clients to vastly inflate their orders when
 * they reach the top of book but I have assumed that amend will be an allowed type here.
 */
public enum OrderType {
    NEW,
    AMEND,
    CANCEL
}
