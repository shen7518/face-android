package com.example.face.http;


import com.example.face.model.act.ActInvitedResp;
import com.example.face.model.IdReq;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface InvitedHTTP {

    @POST(value = "invited/reject")
    Observable<Void> agree(@Body IdReq id);

    @POST(value = "invited/reject")
    Observable<Void> reject(@Body IdReq id);

    @GET(value = "invited/list")
    Observable<List<ActInvitedResp>> listInvited();
}
