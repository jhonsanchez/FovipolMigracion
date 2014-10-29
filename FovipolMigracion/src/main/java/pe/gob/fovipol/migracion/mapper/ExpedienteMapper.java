package pe.gob.fovipol.migracion.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface ExpedienteMapper {
	@Select("{ call SAB.PKG_EXPEDIENTE.listar_expediente(#{my_cursor, jdbcType=CURSOR,mode=OUT,javaType=java.sql.ResultSet,resultMap=usuarioMap}) }")
	@Options(statementType = StatementType.CALLABLE)
	public Object listarExpedientes(Map<String,Object> params);
	
	@Select("{call SAB.sp_anularexpediente(#{p_ccexpediente,mode=IN,javaType=string},#{rptaquery,mode=OUT,javaType=int,jdbcType=NUMERIC})}")
	@Options(statementType = StatementType.CALLABLE)
	public void getAnularExpediente(Map<String, Object> params);
	
}
