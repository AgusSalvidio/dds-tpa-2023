package ar.edu.utn.frba.dds.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDelimited extends FileSource {
    @Override
    public Boolean Load() throws IOException {
        BufferedReader _bufferedReader = new BufferedReader(new FileReader(this.path.toString()));
        try {
            String _line = _bufferedReader.readLine();
            while ( null != _line ) {
                String [] _fields = _line.split(this.columndelimiter);




                //System.out.println(Arrays.toString(_fields));
                _line = _bufferedReader.readLine();
            }

        } catch (Exception e) {
            //TODO

        } finally {
            if ( _bufferedReader != null ) {
                _bufferedReader.close();
            }
        }


        //TODO
        return null;
    }
}
