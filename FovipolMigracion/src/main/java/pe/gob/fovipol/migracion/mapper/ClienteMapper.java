package pe.gob.fovipol.migracion.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import pe.gob.fovipol.migracion.model.Aporte;
import pe.gob.fovipol.migracion.model.Cliente;

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

	@Select("SELECT C.C_C_CLIENTE AS codicl,C.C_T_CLIENTE AS nococl FROM S10CLI C WHERE C.C_T_CIP=#{ciptcl} OR C.C_T_CODOFIN=#{coficl}")
    public Cliente getCustomerDet(Cliente cliente);
	
	@Select("SELECT COUNT(*)AS total FROM S10CLIBEN WHERE C_C_CLIENTE=#{codtit}")
	public Cliente getTotalBen(Cliente cliente);
	
	@Select("{call SAB.SP_TRASPASARAPORTES(#{pcodben,mode=IN,javaType=string},#{pcodtit,mode=IN,javaType=string})}")
	@Options(statementType = StatementType.CALLABLE)
	public void getTraspasaAporte(Map<String, Object> params);
	
}
