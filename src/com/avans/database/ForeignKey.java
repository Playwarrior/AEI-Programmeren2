package com.avans.database;

public class ForeignKey extends Column {

    private Action action;
    private Response response;

    public ForeignKey(String name, Type type, Action action, Response response, int... args) {
        super(name, type, null, false, args);

        this.action = action;
        this.response = response;
    }

    public Action getAction() {
        return action;
    }

    public Response getResponse() {
        return response;
    }

    public enum Action {

        CREATE,
        UPDATE,
        DELETE;

        @Override
        public String toString(){
            return String.format("ON %s", name());
        }
    }

    public enum Response {

        NO_ACTION,
        UPDATE,
        CASCADE;

        @Override
        public String toString() {
            return name().replaceAll("_", " ");
        }
    }
}
