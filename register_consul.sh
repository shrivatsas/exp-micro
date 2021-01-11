docker inspect micro-exp_express_1 --format '{{.NetworkSettings.Networks.microexp.IPAddress}}' | xargs -I '{}' sed 's/<ipaddress>/{}/' expressjs/express-consul.json | curl --request PUT --data-binary @- http://localhost:8500/v1/agent/service/register

docker inspect micro-exp_fastapi_1 --format '{{.NetworkSettings.Networks.microexp.IPAddress}}' | xargs -I '{}' sed 's/<ipaddress>/{}/' fastapi/fastapi-consul.json | curl --request PUT --data-binary @- http://localhost:8500/v1/agent/service/register

docker inspect micro-exp_gin_1 --format '{{.NetworkSettings.Networks.microexp.IPAddress}}' | xargs -I '{}' sed 's/<ipaddress>/{}/' go/go-consul.json | curl --request PUT --data-binary @- http://localhost:8500/v1/agent/service/register

docker inspect micro-exp_quarkus_1 --format '{{.NetworkSettings.Networks.microexp.IPAddress}}' | xargs -I '{}' sed 's/<ipaddress>/{}/' quarkus/quarkus-consul.json | curl --request PUT --data-binary @- http://localhost:8500/v1/agent/service/register

docker inspect micro-exp_helidon-se_1 --format '{{.NetworkSettings.Networks.microexp.IPAddress}}' | xargs -I '{}' sed 's/<ipaddress>/{}/' helidon-se/helidon-consul.json | curl --request PUT --data-binary @- http://localhost:8500/v1/agent/service/register

docker inspect micro-exp_luminous_1 --format '{{.NetworkSettings.Networks.microexp.IPAddress}}' | xargs -I '{}' sed 's/<ipaddress>/{}/' luminous-clj/luminous-consul.json | curl --request PUT --data-binary @- http://localhost:8500/v1/agent/service/register

docker inspect micro-exp_micronaut_1 --format '{{.NetworkSettings.Networks.microexp.IPAddress}}' | xargs -I '{}' sed 's/<ipaddress>/{}/' micronaut/micronaut-consul.json | curl --request PUT --data-binary @- http://localhost:8500/v1/agent/service/register

docker inspect micro-exp_elixir_1 --format '{{.NetworkSettings.Networks.microexp.IPAddress}}' | xargs -I '{}' sed 's/<ipaddress>/{}/' elixir/hello/elixir-consul.json | curl --request PUT --data-binary @- http://localhost:8500/v1/agent/service/register
