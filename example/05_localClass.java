class SimpleLocal {
	class Inher {}

	public int field = 15;

	public void inc(int x) {

		class Local3 {
			public int anonField = 4;
			public void run (int a) {
				int x = 15;
				// omp parallel
				{
					int foo = anonField + this.anonField + x + a + field;
					foo = anonField;
					foo = this.anonField;
					foo = x;
					foo = a;
					foo = field;
				}
			}
		}
	}
	// complex example - params and locals must be declared final
	class Nested1 {
		int f1 = 0;
		void m1(final int n1) {
			final int l1 = 0;
			class Local1 {
				int f2 = 0;
				class Nested2 {
					int f3 = 0;
					void m2(final int n2){
						final int l2 = 0;
						class Local2 {
							int f4 = 0;
							void m3(final int n3) {
								final int l3 = 0;
								// omp parallel
								{
									int temp = 
										field +
										f1 +
										f2 +
										f3 +
										f4 +
										n1 +
										n2 +
										n3 +
										l1 +
										l2 +
										l3;

								}
							}
						}
						
					}
				}
			}
		}
	}

	class Nested2 {
		int field1;
		void m1(final int x) {
			// omp parallel
			{
				class Local {
					int field2;
					void m2(int y) {
						int tmp = x + y + field1 + field2;
					}
				}
			}
		}
	}
}
