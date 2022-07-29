import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
    private static final Logger log = Logger.getLogger(GrpcServer.class.getName());

        public static void main(String[] args) throws Exception {
            Server server = ServerBuilder
                    .forPort(8080)
                    .addService(new HelloServiceImpl())
                    .build();

            log.info("Servidor Iniciado");
            server.start();
            server.awaitTermination();
        }

}
