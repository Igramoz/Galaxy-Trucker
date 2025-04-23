package model.carte.zonaDiGuerra;

public class PenalitaConCriterio {
		private final Criterio criterio;
		private final Penalita penalita;
		
		private final Object valore; // valore attribuito alla penalita
		// è sempre un integer, a parte nel caso delle cannonate dove è una lista di colpi
		
		public PenalitaConCriterio(Criterio criterio, Penalita penalita, Object valore) {
			this.criterio = criterio;
			this.penalita = penalita;
			this.valore = valore;
		}

		public Criterio getCriterio() {
			return criterio;
		}

		public Penalita getPenalita() {
			return penalita;
		}

		public Object getValore() {
			return valore;
		}
}