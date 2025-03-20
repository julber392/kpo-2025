package hse.kpo.bighomework1.entity;

import hse.kpo.bighomework1.data.ReportFormat;

import java.util.Map;

public interface Exportable {
    Map<String,String> export(ReportFormat format) throws IllegalArgumentException;
}
