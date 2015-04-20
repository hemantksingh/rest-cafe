package com.example.helloworld;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;

/**
 * Created by hkumar on 17/04/2015.
 */
@Path("/{applicationId}/document")
@Produces({"application/pdf"})
public class DocumentResource {

    @GET
    @Timed
    public StreamingOutput getDocument(@PathParam("applicationId") String applicationId) {
        return output -> new PdfDocument("resources/generated.pdf")
                .writeTo(output);
    }
}

class PdfDocument{
    private final String documentName;

    public PdfDocument(String documentName) {
        this.documentName = documentName;
    }

    public void writeTo(OutputStream output) {
        try {
            InputStream input = new FileInputStream(new File(this.documentName));

            int c;
            byte[] buf = new byte[8192];

            while ((c = input.read(buf, 0, buf.length)) > 0) {
                output.write(buf, 0, c);
                output.flush();
            }

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
