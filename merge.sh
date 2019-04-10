#!/bin/bash
set -e 
set -o pipefail


merge()
{
    (cd .. && cd $1 && pwd && (git add -A && git commit -m "version bump" || true ) && git checkout master-github && git merge --squash master --allow-unrelated-histories && (git commit -m "merged" || true ) )
}


# ( cd .. && \ 
# (grep -rl '<idempiere-micro.version>0.21.0' ./**/pom.xml | xargs -r sed -i 's/<idempiere-micro.version>0.21.0/<idempiere-micro.version>0.23.0/g' || true) && \
# ( grep -rl '<idempiere-micro.version>0.22.0' ./**/pom.xml | xargs -r sed -i 's/<idempiere-micro.version>0.22.0/<idempiere-micro.version>0.23.0/g' || true ) && \
# ( grep -rl 'IDEMPIERE_MICRO_VERSION: 0.21.0' ./**/.circleci/config.yml | xargs -r sed -i 's/IDEMPIERE_MICRO_VERSION: 0.21.0/IDEMPIERE_MICRO_VERSION: 0.23.0/g' || true ) && \
# ( grep -rl 'IDEMPIERE_MICRO_VERSION: 0.22.0' ./**/.circleci/config.yml | xargs -r sed -i 's/IDEMPIERE_MICRO_VERSION: 0.22.0/IDEMPIERE_MICRO_VERSION: 0.23.0/g' || true ) )

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
