package pe.gob.fovipol.migracion.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;


public interface ClienteMapper {
	
	@Select("{ call SAB.pqspcustomer.my_procedurecustomer(#{my_cursorcustomer, jdbcType=CURSOR,mode=OUT,javaType=java.sql.ResultSet,resultMap=clienteMap},"
			+ "#{P_C_C_CLIENTE,mode=IN,javaType=string},#{TIPO,mode=IN,javaType=int}) }")
	@Options(statementType = StatementType.CALLABLE)
	public Object getCustomer(Map<String,Object> params);
	
}
