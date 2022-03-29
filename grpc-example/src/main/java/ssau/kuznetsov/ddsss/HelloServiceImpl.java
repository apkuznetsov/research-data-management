package ssau.kuznetsov.ddsss;

import io.grpc.stub.StreamObserver;
import ssau.kuznetsov.proto.HelloRequest;
import ssau.kuznetsov.proto.HelloResponse;

public class HelloServiceImpl {

    // If we compare the signature of hello() with the one we wrote in the HellService.proto file,
    // we'll notice that it does not return HelloResponse.
    // Instead, it takes the second argument as StreamObserver<HelloResponse>,
    // which is a response observer, a call back for the server to call with its response.
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = "Hello, " +
                request.getFirstName() +
                " " +
                request.getLastName();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        // Finally, we need to call onCompleted() to specify that we’ve finished dealing with the RPC,
        // else the connection will be hung, and the client will just wait for more information to come in.
        responseObserver.onCompleted();
    }
}
