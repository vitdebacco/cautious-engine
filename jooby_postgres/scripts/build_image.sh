docker build --tag=envylabs/cautious-engine_jooby-random .
docker rmi $(docker images --filter 'dangling=true' -q --no-trunc) &> /dev/null || true &> /dev/null
