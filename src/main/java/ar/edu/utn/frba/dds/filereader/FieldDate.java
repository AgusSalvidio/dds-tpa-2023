package ar.edu.utn.frba.dds.filereader;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

public class FieldDate extends Field {

    public FieldDate(Integer _index, String _name, Integer _length) {
        super(_index, _name, _length);
    }

    @Override
    public void setValue(String _value) {
        dateTimeValue = LocalDateTime.parse(_value);
    }

    @Override
    public LocalDateTime getDateValue() { return dateTimeValue; }

}
