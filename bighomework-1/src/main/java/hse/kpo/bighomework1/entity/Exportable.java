package hse.kpo.bighomework1.entity;

import hse.kpo.bighomework1.data.ReportFormat;

public interface Exportable {
    String export(ReportFormat format);
}
