package ar.edu.utn.frba.dds.services.georef;

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

  public static ServiceGeoref getInstance() {
    if (instance == null) {
      instance = new ServiceGeoref();
    }
    return instance;
  }

  public ProvinceCollection provinceCollection() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ProvinceCollection> requestProvinces = georefService.provinces();
    Response<ProvinceCollection> responseProvinces = requestProvinces.execute();
    return responseProvinces.body();
  }

  public MunicipalityCollection municipalityCollectionFilteredBy(int id) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<MunicipalityCollection> municipalitiesRequest = georefService.municipalities(id);
    Response<MunicipalityCollection> municipalitiesResponse = municipalitiesRequest.execute();
    return municipalitiesResponse.body();
  }

  public MunicipalityCollection municipalityCollectionFor(Province province) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<MunicipalityCollection> municipalitiesRequest =
        georefService.municipalities(province.id, "id, nombre", maxEntriesQuantity);
    Response<MunicipalityCollection> municipalitiesResponse = municipalitiesRequest.execute();
    return municipalitiesResponse.body();
  }

}
