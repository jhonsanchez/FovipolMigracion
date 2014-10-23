package pe.gob.fovipol.migracion.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface AportesMapper {
	
	@Select("{ call SAB.PQSPAPORTES.my_procedureaportes(#{my_cursoraportes, jdbcType=CURSOR,mode=OUT,javaType=java.sql.ResultSet,resultMap=aportesMap},"
			+ "#{P_C_C_CLIENTE,mode=IN,javaType=string},#{TIPOAPORTE,mode=IN,javaType=string}) }")
	@Options(statementType = StatementType.CALLABLE)
	public Object getLstAportes(Map<String,Object> params);
}
