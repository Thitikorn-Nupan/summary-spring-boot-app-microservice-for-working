package com.ttknp.abcmodelsservice.models.common;

import java.util.ArrayList;
import java.util.List;


public class DataTableModelService {

    // request
    public static class Request {

        private Integer maxLength;
        private OrderBy orderBy;

        public Request( Integer maxLength, OrderBy orderBy) {
            this.maxLength = maxLength;
            this.orderBy = orderBy;
        }

        public Integer getMaxLength() {
            return maxLength;
        }

        public void setMaxLength(Integer maxLength) {
            this.maxLength = maxLength;
        }

        public OrderBy getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(OrderBy orderBy) {
            this.orderBy = orderBy;
        }


        @Override
        public String toString() {
            return "Request{" +
                    ", maxLength=" + maxLength +
                    ", orderBy=" + orderBy +
                    '}';
        }
    }

    public static class OrderBy {

        private String name;
        private String direction;

        public OrderBy(String theName, String theDirection) {
            this.name = theName;
            this.direction = theDirection;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDirection() {
            return this.direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "OrderBy{" +
                    "name='" + name + '\'' +
                    ", direction='" + direction + '\'' +
                    '}';
        }
    }

    // response
    public static class Response<T> {
        private Integer recordsTotal;
        private Integer recordsActual;
        private String error;
        private List<T> data;

        public Response(Request request, String error, List<T> data) { // passed on resposne
            this.recordsTotal = data.size();
            this.data = getData(data, request.getMaxLength());
            this.recordsActual = this.data.size();
            this.error = error;
        }

        private List<T> getData(List<T> data,Integer maxLength) {
            List<T> dataHold = new ArrayList<>();
            for (int i = 0; i < maxLength; i++) {
                try {
                    dataHold.add(data.get(i));
                } catch (Exception e) {
                    System.out.println("data out of length");
                    return dataHold;
                }
            }
            return dataHold;
        }

        public Integer getRecordsTotal() {
            return recordsTotal;
        }

        public void setRecordsTotal(Integer recordsTotal) {
            this.recordsTotal = recordsTotal;
        }

        public Integer getRecordsActual() {
            return recordsActual;
        }

        public void setRecordsActual(Integer recordsActual) {
            this.recordsActual = recordsActual;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "recordsTotal=" + recordsTotal +
                    ", recordsActual=" + recordsActual +
                    ", error='" + error + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}
