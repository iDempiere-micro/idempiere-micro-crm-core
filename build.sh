#!/bin/bash
set -e 
set -o pipefail

purge()
{
    ( cd .. && cd $1 && mvn clean )
}
build()
{
    ( cd .. && cd $1 && mvn clean install -U )
}

purge idempiere-micro-session-core
purge idempiere-micro-base-interfaces
purge idempiere-micro-model-core
purge idempiere-micro-model-interfaces
purge idempiere-micro-crm-core
purge idempiere-micro-product-core
purge idempiere-micro-tax-core
purge idempiere-micro-process-core
purge idempiere-micro-order-core
purge idempiere-micro-invoicing-core
purge idempiere-micro-liberty-standalone

build idempiere-micro-session-core
build idempiere-micro-base-interfaces
build idempiere-micro-model-core
build idempiere-micro-model-interfaces
build idempiere-micro-crm-core
build idempiere-micro-product-core
build idempiere-micro-tax-core
build idempiere-micro-process-core
build idempiere-micro-order-core
build idempiere-micro-invoicing-core
build idempiere-micro-liberty-standalone
