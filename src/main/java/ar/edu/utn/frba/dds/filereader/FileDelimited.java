package ar.edu.utn.frba.dds.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDelimited extends FileSource {

    public FileDelimited(String _name) {
        super(_name);
    }

    @Override
    public List<Field> GetLine() throws IOException {
        List<Field> _row = new ArrayList<>();
        Integer i = -1;
        try {
            String _line = this._bufferedReader.readLine();
            while ( null != _line ) {
                //Parse Line
                String [] _fields = _line.split(this.columndelimiter);
                //Make Line & Fields
                _row = this.getFields();
                for (Field _field: _row) {
                    _field.value = _fields[i];
                }
            }
        } catch (Exception e) {
            _row = null;
        } finally {
            return _row;
        }
    }

}
