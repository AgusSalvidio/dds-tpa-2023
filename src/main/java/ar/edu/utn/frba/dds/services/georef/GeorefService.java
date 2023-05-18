package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.MunicipalityCollection;
import ar.edu.utn.frba.dds.services.georef.entities.ProvinceCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
  @GET("provincias")
  Call<ProvinceCollection> provinces();

  @GET("provincias")
  Call<ProvinceCollection> provinces(@Query("campos") String fields);

  @GET("municipios")
  Call<MunicipalityCollection> municipalities(@Query("provincia") int provinceId);

  @GET("municipios")
  Call<MunicipalityCollection> municipalities(
      @Query("provincia") int provinceId,
      @Query("campos") String fields,
      @Query("max") int max);
}
