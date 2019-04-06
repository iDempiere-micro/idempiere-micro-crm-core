package org.compiere.crm

import kotliquery.Row

/**
 * Address transaction model
 *
 * @author Elaine
 */
class MAddressTransaction : X_C_AddressTransaction {

    constructor(C_AddressTransaction_ID: Int) : super(C_AddressTransaction_ID) {}

    constructor(row: Row) : super(row) {}

    companion object {
        private const val serialVersionUID = 8572809249265680649L
    }
}
