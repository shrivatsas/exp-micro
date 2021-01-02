# https://dev.to/hlappa/development-environment-for-elixir-phoenix-with-docker-and-docker-compose-2g17

FROM bitwalker/alpine-elixir-phoenix:latest

WORKDIR /app

COPY mix.exs .
COPY mix.lock .

RUN mkdir assets

COPY assets/package.json assets
COPY assets/package-lock.json assets

EXPOSE 9000
CMD mix deps.get && cd assets && npm install && cd .. && mix phx.server
