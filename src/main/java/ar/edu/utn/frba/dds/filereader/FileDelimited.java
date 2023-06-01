package ar.edu.utn.frba.dds.filereader;

import java.io.IOException;

public class FileDelimited extends DataFile {

    public FileDelimited(String _name) {
        super(_name);
    }

    @Override
    public String[] parseLine() {
        try {
            String[] _fields = this._bufferedReader.readLine().split(this.colDelimiter);
            return _fields;
        } catch (IOException e) {
            return null;
        }
    }

}
