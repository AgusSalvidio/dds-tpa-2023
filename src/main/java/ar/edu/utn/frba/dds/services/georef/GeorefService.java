package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.DepartmentCollection;
import ar.edu.utn.frba.dds.services.georef.entities.MunicipalityCollection;
import ar.edu.utn.frba.dds.services.georef.entities.ProvinceCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {

  @GET("provincias")
  Call<ProvinceCollection> provinces();

  @GET("provincias")
  Call<ProvinceCollection> provinces(
      @Query("nombre") String name,
      @Query("campos") String fields);

  @GET("provincias")
  Call<ProvinceCollection> provinces(@Query("campos") String fields);

  @GET("municipios")
  Call<MunicipalityCollection> municipalities();

  @GET("municipios")
  Call<MunicipalityCollection> municipalities(@Query("provincia") String provinceId);

  @GET("municipios")
  Call<MunicipalityCollection> municipalities(
      @Query("provincia") String provinceId,
      @Query("campos") String fields,
      @Query("max") int max);

  @GET("departamentos")
  Call<DepartmentCollection> departments();

  @GET("departamentos")
  Call<DepartmentCollection> departments(
      @Query("provincia") String provinceId,
      @Query("campos") String fields,
      @Query("max") int max);
}
