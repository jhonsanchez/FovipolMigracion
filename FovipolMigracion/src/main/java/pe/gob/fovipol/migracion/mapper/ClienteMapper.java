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

	@Update("UPDATE S10APOACT SET N_N_APORTEE07=#{nimonto}"
			+ "WHERE C_C_CLIENTE=(SELECT CL.C_C_CLIENTE "
			+ "FROM S10CLI CL WHERE CL.C_T_CIP=#{ctcip} AND  CL.C_T_CODOFIN=#{ctcodofin}) "
			+ "AND N_I_ANHIO = #{nianhio}")
	public int getUpdateAportes(Aporte aporte);
}
