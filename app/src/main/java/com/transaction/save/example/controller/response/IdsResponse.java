package com.transaction.save.example.controller.response;

import java.util.List;

public class IdsResponse extends SuccessResponse {
    private final List<Long> ids;

    public IdsResponse(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }

}
