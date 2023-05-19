package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.DepartmentCollection;
import ar.edu.utn.frba.dds.services.georef.entities.MunicipalityCollection;
import ar.edu.utn.frba.dds.services.georef.entities.Province;
import ar.edu.utn.frba.dds.services.georef.entities.ProvinceCollection;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGeoref {
  private static ServiceGeoref instance = null;
  private static int maxEntriesQuantity = 200;
  private static final String urlAPI = "https://apis.datos.gob.ar/georef/api/";
  private Retrofit retrofit;

  private ServiceGeoref() {
    this.retrofit =
        new Retrofit
            .Builder().baseUrl(urlAPI).addConverterFactory(GsonConverterFactory.create()).build();
  }

  public static ServiceGeoref instance() {
    if (instance == null) {
      instance = new ServiceGeoref();
    }
    return instance;
  }

  public ProvinceCollection provinceCollection() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ProvinceCollection> provincesRequest = georefService.provinces();
    Response<ProvinceCollection> provincesResponse = provincesRequest.execute();
    return provincesResponse.body();
  }

  public ProvinceCollection provinceCollectionFilteredBy(String fields)
      throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ProvinceCollection> provincesRequest = georefService.provinces(fields);
    Response<ProvinceCollection> provincesResponse = provincesRequest.execute();
    return provincesResponse.body();
  }

  public ProvinceCollection provinceCollectionFilteredByName(String name, String fields)
      throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ProvinceCollection> provincesRequest = georefService.provinces(name, fields);
    Response<ProvinceCollection> provincesResponse = provincesRequest.execute();
    return provincesResponse.body();
  }

  public MunicipalityCollection municipalityCollection() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<MunicipalityCollection> municipalitiesRequest = georefService.municipalities();
    Response<MunicipalityCollection> municipalitiesResponse = municipalitiesRequest.execute();
    return municipalitiesResponse.body();
  }

  public MunicipalityCollection municipalityCollectionFilteredBy(
      String id,
      String fields,
      int max) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<MunicipalityCollection> municipalitiesRequest =
        georefService.municipalities(id, fields, max);
    Response<MunicipalityCollection> municipalitiesResponse = municipalitiesRequest.execute();
    return municipalitiesResponse.body();
  }

  public MunicipalityCollection municipalityCollectionFor(Province province) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<MunicipalityCollection> municipalitiesRequest =
        georefService.municipalities(province.id(), "id, nombre", maxEntriesQuantity);
    Response<MunicipalityCollection> municipalitiesResponse = municipalitiesRequest.execute();
    return municipalitiesResponse.body();
  }

  public DepartmentCollection departmentCollection() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<DepartmentCollection> departmentsRequest = georefService.departments();
    Response<DepartmentCollection> departmentsResponse = departmentsRequest.execute();
    return departmentsResponse.body();
  }

  public DepartmentCollection departmentCollectionFilteredBy(
      String id,
      String fields,
      int max) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<DepartmentCollection> departmentsRequest = georefService.departments(id, fields, max);
    Response<DepartmentCollection> departmentsResponse = departmentsRequest.execute();
    return departmentsResponse.body();
  }
}
