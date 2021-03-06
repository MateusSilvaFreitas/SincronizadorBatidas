package service;

import dto.DadosBatidaDTO;

import java.awt.MouseInfo;
import java.awt.Point;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MoveMouseService {
	
	Point old; 
	int tempo;
	int qntdVezesParado = 0;
	boolean isInativo;
	Long tempoUltimaAcao;
	Long qntdTempoInativo = 0L;
	Long qntdTempoUltimaAcao = 0l;
	Long maiorTempoInativo = 0l;

	public void verificarMouse(long tempo, long capturasInativo, long idUsuario) {
		LocalDateTime horarioBatidaInicio = LocalDateTime.now();
		Timer timer = new Timer("chupa-cabra");
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				var actual = MouseInfo.getPointerInfo().getLocation();
				if(actual.equals(old)) {
					qntdVezesParado++;
				} else {
					old = actual;
					qntdVezesParado = 0;
					tempoUltimaAcao = System.currentTimeMillis();
				}
				if(qntdVezesParado > capturasInativo) {
					qntdTempoInativo += tempo;
					qntdTempoUltimaAcao = tempo * qntdVezesParado;
					if(qntdTempoUltimaAcao > maiorTempoInativo){
						maiorTempoInativo = qntdTempoUltimaAcao;
					}
				}
				try {
					JsonService.gravaArquivoJSONInatividade(new DadosBatidaDTO(horarioBatidaInicio, null, maiorTempoInativo, qntdTempoInativo, idUsuario));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task , new Date(), tempo*1000);
	}
}
