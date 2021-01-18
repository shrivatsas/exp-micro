defmodule HelloWeb.APIController do
  use HelloWeb, :controller

  def index(conn, _params) do
    json conn, "Hello World"
  end
end
