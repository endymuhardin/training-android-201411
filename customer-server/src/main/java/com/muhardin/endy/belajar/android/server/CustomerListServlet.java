package com.muhardin.endy.belajar.android.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        // sample data
        Map<String, Object> customer1 = new LinkedHashMap<String, Object>();
        customer1.put("_id", "1");
        customer1.put("nama", "Endy Muhardin");
        customer1.put("tanggalLahir", new Date().getTime());
        customer1.put("domisili", "Jakarta");
        customer1.put("alamat", "Cililitan");

        Map<String, Object> customer2 = new LinkedHashMap<String, Object>();
        customer2.put("_id", "2");
        customer2.put("nama", "Mansyur");
        customer2.put("tanggalLahir", new Date().getTime());
        customer2.put("domisili", "Banten");
        customer2.put("alamat", "Rangkas");

        Map<String, Object> customer3 = new LinkedHashMap<String, Object>();
        customer3.put("_id", "3");
        customer3.put("nama", "Mulyana");
        customer3.put("tanggalLahir", new Date().getTime());
        customer3.put("domisili", "Bogor");
        customer3.put("alamat", "Wr. Jambu");

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.add(customer1);
        data.add(customer2);
        data.add(customer3);

        // konversi ke JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String output = mapper.writeValueAsString(data);

        resp.getWriter().print(output);
        resp.getWriter().flush();
    }
}
