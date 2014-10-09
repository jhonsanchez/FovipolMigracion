package pe.gob.fovipol.migracion.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface UsuarioMapper {
	
	@Select("{ call SAB.mypackage.my_procedure(#{my_cursor, jdbcType=CURSOR,mode=OUT,javaType=java.sql.ResultSet,resultMap=usuarioMap}) }")
	@Options(statementType = StatementType.CALLABLE)
	public Object getEmployees(Map<String,Object> params);

}
