docker build --tag=envylabs/cautious-engine_quarkus-random .
docker rmi $(docker images --filter 'dangling=true' -q --no-trunc) &> /dev/null || true &> /dev/null
