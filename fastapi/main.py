from fastapi import FastAPI
from opentelemetry.instrumentation.fastapi import FastAPIInstrumentor
from opentelemetry import trace
from opentelemetry.exporter import zipkin
from opentelemetry.sdk.trace import TracerProvider
from opentelemetry.sdk.trace.export import BatchExportSpanProcessor

trace.set_tracer_provider(TracerProvider())

zipkin_exporter = zipkin.ZipkinSpanExporter(
    service_name="my-helloworld-service",
    host_name="localhost",
    port=9411,
    endpoint='/api/v2/spans'
)

trace.get_tracer_provider().add_span_processor(
    BatchExportSpanProcessor(zipkin_exporter)
)

tracer = trace.get_tracer(__name__)

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}

FastAPIInstrumentor.instrument_app(app)    