package model.carte.criteriEffetti;

public class CriterioConEffetto {
		private final Criterio criterio;
		private final Effetto effetto;
		
		private final Object valore; // valore attribuito alla penalita
		// è sempre un integer, a parte nel caso delle cannonate dove è una lista di colpi
		
		public CriterioConEffetto(Criterio criterio, Effetto effetto, Object valore) {
			this.criterio = criterio;
			this.effetto = effetto;
			this.valore = valore;
		}

		public Criterio getCriterio() {
			return criterio;
		}

		public Effetto getPenalita() {
			return effetto;
		}

		public Object getValore() {
			return valore;
		}
}