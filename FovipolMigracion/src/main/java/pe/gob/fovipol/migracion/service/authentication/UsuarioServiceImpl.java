package pe.gob.fovipol.migracion.service.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.fovipol.migracion.mapper.UsuarioMapper;
import pe.gob.fovipol.migracion.model.Usuario;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	private UsuarioMapper usuarioMapper;
	@Override
	public List<Usuario> getEmployees() {
		Map<String,Object> params = new HashMap<String,Object>();
		usuarioMapper.getEmployees(params);
		return ((ArrayList<Usuario>)params.get("my_cursor")); 
	}

}
