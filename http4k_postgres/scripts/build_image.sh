docker build --tag=envylabs/cautious-engine_http4k-random .
docker rmi $(docker images --filter 'dangling=true' -q --no-trunc) &> /dev/null || true &> /dev/null
