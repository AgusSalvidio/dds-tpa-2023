package ar.edu.utn.frba.dds.filereader;

public abstract class DataColumn {
    String name;
    Integer precision;
    String fieldsource;
    String value;

    public DataColumn(String _name, Integer _precision, String _fieldsource) {
        this.name = _name;
        this.precision = _precision;
        this.fieldsource = _fieldsource;
        this.value = null;

        return;
    }

}
