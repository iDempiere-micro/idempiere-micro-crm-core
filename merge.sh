#!/bin/bash
set -e 
set -o pipefail


merge()
{
    ( cd .. && cd $1 && pwd && \
    (grep -rl '<idempiere-micro.version>0.22.0' . | xargs -r sed -i 's/<idempiere-micro.version>0.22.0/<idempiere-micro.version>0.22.0/g' || true) && \
    ( grep -rl '<idempiere-micro.version>0.22.0' . | xargs -r sed -i 's/<idempiere-micro.version>0.22.0/<idempiere-micro.version>0.22.0/g' || true ) && \
    ( grep -rl 'IDEMPIERE_MICRO_VERSION: 0.22.0' . | xargs -r sed -i 's/IDEMPIERE_MICRO_VERSION: 0.22.0/IDEMPIERE_MICRO_VERSION: 0.22.0/g' || true ) && \
    ( grep -rl 'IDEMPIERE_MICRO_VERSION: 0.22.0' . | xargs -r sed -i 's/IDEMPIERE_MICRO_VERSION: 0.22.0/IDEMPIERE_MICRO_VERSION: 0.22.0/g' || true ) && \
    (git add -A && git commit -m "version bump" || true ) && \
    git merge --squash master --allow-unrelated-histories && (git commit -m "merged" || true ) && (git tag -a v0.22.0 -m "0.22.0" || true ) )
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
