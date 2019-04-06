#!/bin/bash
set -e 
set -o pipefail


merge()
{
    ( cd .. && cd $1 && pwd && \
    (git tag -a 0.22.0 -m "0.22.0" || true ) ) \
    git push --tags
}

merge idempiere-micro-session-core
merge idempiere-micro-base-interfaces
merge idempiere-micro-model-core
merge idempiere-micro-model-interfaces
merge idempiere-micro-crm-core
merge idempiere-micro-product-core
merge idempiere-micro-tax-core
merge idempiere-micro-process-core
merge idempiere-micro-order-core
merge idempiere-micro-invoicing-core
merge idempiere-micro-liberty-standalone
