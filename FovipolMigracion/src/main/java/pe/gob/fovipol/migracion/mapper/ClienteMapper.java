package pe.gob.fovipol.migracion.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import pe.gob.fovipol.migracion.model.Aporte;

public interface ClienteMapper {

	@Select("{ call SAB.pqspcustomer.my_procedurecustomer(#{my_cursorcustomer, jdbcType=CURSOR,mode=OUT,javaType=java.sql.ResultSet,resultMap=clienteMap},"
			+ "#{P_C_C_CLIENTE,mode=IN,javaType=string},#{TIPO,mode=IN,javaType=int}) }")
	@Options(statementType = StatementType.CALLABLE)
	public Object getCustomer(Map<String, Object> params);

	@Select("{call SAB.sp_updateAporteMasivo("
			+ "#{nomcolumnames,mode=IN,javaType=string},"
			+ "#{nimonto,mode=IN,javaType=double},"
			+ "#{nianhio,mode=IN,javaType=int},"
			+ "#{ctcip,mode=IN,javaType=string},"
			+ "#{ctcodofin,mode=IN,javaType=string},"
			+ "#{rptaquery,mode=OUT,javaType=int,jdbcType=NUMERIC}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void getUpdateAportes(Map<String, Object> params);
	
}
