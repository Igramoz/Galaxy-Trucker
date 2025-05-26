package model.carte.criteriEffetti;

import java.util.Objects;

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

		@Override
		public int hashCode() {
			return Objects.hash(criterio, effetto, valore);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CriterioConEffetto other = (CriterioConEffetto) obj;
			return criterio == other.criterio && effetto == other.effetto && Objects.equals(valore, other.valore);
		}
}