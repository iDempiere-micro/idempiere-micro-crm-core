#!/bin/bash
set -e 
set -o pipefail

clone()
{
    ( cd .. && git clone https://hsharpsoftware@bitbucket.org/idempiere-micro/$1.git )
}

clone idempiere-micro-session-core
clone idempiere-micro-base-interfaces
clone idempiere-micro-model-core
clone idempiere-micro-model-interfaces
clone idempiere-micro-product-core
clone idempiere-micro-tax-core
clone idempiere-micro-process-core
clone idempiere-micro-order-core
clone idempiere-micro-invoicing-core
clone idempiere-micro-liberty-standalone
