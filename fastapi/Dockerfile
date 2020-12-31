FROM tiangolo/uvicorn-gunicorn-fastapi:python3.7-alpine3.8

COPY . /app
RUN apk update
RUN apk --no-cache add gcc musl-dev

# Install Poetry
RUN wget -q -O - https://raw.githubusercontent.com/python-poetry/poetry/master/get-poetry.py | python
RUN ln -s ${HOME}/.poetry/bin/poetry /bin/poetry
RUN poetry config virtualenvs.create false

RUN poetry install --no-root --no-dev

ENV PYTHONPATH=/app