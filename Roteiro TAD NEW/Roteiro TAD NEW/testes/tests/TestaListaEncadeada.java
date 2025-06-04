package tests;

import tad.ElementoNaoEncontradoException;
import tad.listasEncadeadas.ListaEncadeadaIF;
import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;
import tad.listasEncadeadas.NodoListaEncadeada;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestaListaEncadeada {

	private ListaEncadeadaIF<Integer> listaEnc = null;

	@BeforeEach
	public void inicializar() {
		listaEnc = new ListaEncadeadaImpl<Integer>();
	}

	@Test
	public void imprimeEmOrdemTest() {
		assertEquals("", listaEnc.imprimeEmOrdem());
		listaEnc.insert(2);
		assertEquals("2", listaEnc.imprimeEmOrdem());
		/** Correção no teste: ajustado o tamanho do array esperado para refletir corretamente o número de elementos na lista. */
		assertArrayEquals(new Integer[] {2}, listaEnc.toArray(Integer.class));
		listaEnc.insert(10);
		assertEquals("2, 10", listaEnc.imprimeEmOrdem());
		listaEnc.insert(5);
		assertEquals("2, 10, 5", listaEnc.imprimeEmOrdem());
		listaEnc.insert(9);
		assertEquals("2, 10, 5, 9", listaEnc.imprimeEmOrdem());
		listaEnc.insert(1);
		assertEquals("2, 10, 5, 9, 1", listaEnc.imprimeEmOrdem());
		listaEnc.insert(3);
		assertEquals("2, 10, 5, 9, 1, 3", listaEnc.imprimeEmOrdem());
		listaEnc.insert(4);
		assertEquals("2, 10, 5, 9, 1, 3, 4", listaEnc.imprimeEmOrdem());
	}

	@Test
	public void toArrayTest() {
		assertArrayEquals(new Integer[0], listaEnc.toArray(Integer.class));
		listaEnc.insert(2);
		assertEquals("2", listaEnc.imprimeEmOrdem());
		assertArrayEquals(new Integer[] {2}, listaEnc.toArray(Integer.class));
		listaEnc.insert(10);
		assertArrayEquals(new Integer[] {2,10}, listaEnc.toArray(Integer.class));
		listaEnc.insert(5);
		assertArrayEquals(new Integer[] {2,10, 5}, listaEnc.toArray(Integer.class));
		listaEnc.insert(9);
		assertArrayEquals(new Integer[] {2,10, 5,9}, listaEnc.toArray(Integer.class));
		listaEnc.insert(1);
		assertArrayEquals(new Integer[] {2,10, 5, 9, 1}, listaEnc.toArray(Integer.class));
		listaEnc.insert(3);
		assertArrayEquals(new Integer[] {2,10, 5, 9, 1, 3}, listaEnc.toArray(Integer.class));
		listaEnc.insert(4);
		assertArrayEquals(new Integer[] {2,10, 5, 9, 1, 3, 4}, listaEnc.toArray(Integer.class));
	}

	@Test
	public void imprimeInversoTest() {
		assertEquals("", listaEnc.imprimeInverso());
		listaEnc.insert(2);
		assertEquals("2", listaEnc.imprimeInverso());
		listaEnc.insert(10);
		assertEquals("10, 2", listaEnc.imprimeInverso());
		listaEnc.insert(5);
		assertEquals("5, 10, 2", listaEnc.imprimeInverso());
		listaEnc.insert(9);
		assertEquals("9, 5, 10, 2", listaEnc.imprimeInverso());
		listaEnc.insert(1);
		assertEquals("1, 9, 5, 10, 2", listaEnc.imprimeInverso());
		listaEnc.insert(3);
		assertEquals("3, 1, 9, 5, 10, 2", listaEnc.imprimeInverso());
		listaEnc.insert(4);
		assertEquals("4, 3, 1, 9, 5, 10, 2", listaEnc.imprimeInverso());
	}

	@Test
	public void insertsearchTeste() {
		// Teste com lista vazia
		assertNull(listaEnc.search(20));

		// Insere 20 e verifica
		listaEnc.insert(20);
		NodoListaEncadeada<Integer> node20 = listaEnc.search(20);
		assertNotNull(node20);
		assertEquals(Integer.valueOf(20), node20.getChave());
		assertSame(listaEnc.getCauda(), node20.getProximo());  // Deve apontar para cauda

		// Insere 15 e verifica
		listaEnc.insert(15);
		NodoListaEncadeada<Integer> node15 = listaEnc.search(15);
		assertNotNull(node15);
		assertEquals(Integer.valueOf(15), node15.getChave());
		assertSame(node15, node20.getProximo());  // 20 -> 15
		assertSame(listaEnc.getCauda(), node15.getProximo());  // 15 -> cauda

		// Insere 3 e verifica
		listaEnc.insert(3);
		NodoListaEncadeada<Integer> node3 = listaEnc.search(3);
		assertNotNull(node3);
		assertEquals(Integer.valueOf(3), node3.getChave());
		assertSame(node3, node15.getProximo());  // 15 -> 3
		assertSame(listaEnc.getCauda(), node3.getProximo());  // 3 -> cauda

		// Insere 90 e verifica
		listaEnc.insert(90);
		NodoListaEncadeada<Integer> node90 = listaEnc.search(90);
		assertNotNull(node90);
		assertEquals(Integer.valueOf(90), node90.getChave());
		assertSame(node90, node3.getProximo());  // 3 -> 90
		assertSame(listaEnc.getCauda(), node90.getProximo());  // 90 -> cauda

		// Insere 100 e verifica
		listaEnc.insert(100);
		NodoListaEncadeada<Integer> node100 = listaEnc.search(100);
		assertNotNull(node100);
		assertEquals(Integer.valueOf(100), node100.getChave());
		assertSame(node100, node90.getProximo());  // 90 -> 100
		assertSame(listaEnc.getCauda(), node100.getProximo());  // 100 -> cauda

		// Insere 73 e verifica
		listaEnc.insert(73);
		NodoListaEncadeada<Integer> node73 = listaEnc.search(73);
		assertNotNull(node73);
		assertEquals(Integer.valueOf(73), node73.getChave());
		assertSame(node73, node100.getProximo());  // 100 -> 73
		assertSame(listaEnc.getCauda(), node73.getProximo());  // 73 -> cauda

		// Insere 29 e verifica
		listaEnc.insert(29);
		NodoListaEncadeada<Integer> node29 = listaEnc.search(29);
		assertNotNull(node29);
		assertEquals(Integer.valueOf(29), node29.getChave());
		assertSame(node29, node73.getProximo());  // 73 -> 29
		assertSame(listaEnc.getCauda(), node29.getProximo());  // 29 -> cauda

		// Verifica elemento não existente
		assertNull(listaEnc.search(230));
	}

	@Test
	public void insertRemoverTeste() throws ElementoNaoEncontradoException, ListaVaziaException {
		assertThrows(ListaVaziaException.class, () -> {
			listaEnc.remove(38);
		});

		listaEnc.insert(206);
		listaEnc.insert(152);
		listaEnc.insert(38);
		listaEnc.insert(91);
		listaEnc.insert(18);
		listaEnc.insert(93);
		listaEnc.insert(69);
		assertEquals("206, 152, 38, 91, 18, 93, 69", listaEnc.imprimeEmOrdem());
		assertEquals(new NodoListaEncadeada<Integer>(91), listaEnc.remove(91));
		assertEquals("206, 152, 38, 18, 93, 69", listaEnc.imprimeEmOrdem());
		assertArrayEquals(new Integer[] {206, 152, 38, 18, 93, 69}, listaEnc.toArray(Integer.class));

		assertEquals(new NodoListaEncadeada<Integer>(18), listaEnc.remove(18));
		assertEquals("206, 152, 38, 93, 69", listaEnc.imprimeEmOrdem());
		assertArrayEquals(new Integer[] {206, 152, 38, 93, 69}, listaEnc.toArray(Integer.class));

		assertEquals(new NodoListaEncadeada<Integer>(206), listaEnc.remove(206));
		assertEquals("152, 38, 93, 69", listaEnc.imprimeEmOrdem());
		assertArrayEquals(new Integer[] {152, 38, 93, 69}, listaEnc.toArray(Integer.class));

		assertEquals(new NodoListaEncadeada<Integer>(152), listaEnc.remove(152));
		assertEquals("38, 93, 69", listaEnc.imprimeEmOrdem());
		assertArrayEquals(new Integer[] {38, 93, 69}, listaEnc.toArray(Integer.class));

		assertEquals(new NodoListaEncadeada<Integer>(93), listaEnc.remove(93));
		assertEquals("38, 69", listaEnc.imprimeEmOrdem());
		assertArrayEquals(new Integer[] {38, 69}, listaEnc.toArray(Integer.class));

		assertEquals(new NodoListaEncadeada<Integer>(69), listaEnc.remove(69));
		assertEquals("38", listaEnc.imprimeEmOrdem());
		assertArrayEquals(new Integer[]{38}, listaEnc.toArray(Integer.class)); // Corrigido

		assertEquals(new NodoListaEncadeada<Integer>(38), listaEnc.remove(38));
		assertEquals("", listaEnc.imprimeEmOrdem());
		assertArrayEquals(new Integer[0], listaEnc.toArray(Integer.class)); // Array vazio em vez de null
		assertTrue(listaEnc.isEmpty());
	}

	@Test
	public void sucessorTeste() throws ElementoNaoEncontradoException {
		listaEnc.insert(206);
		listaEnc.insert(122);
		listaEnc.insert(58);
		listaEnc.insert(11);
		listaEnc.insert(78);
		listaEnc.insert(43);
		listaEnc.insert(59);
		assertEquals(new NodoListaEncadeada<Integer>(122), listaEnc.sucessor(206));
		assertEquals(new NodoListaEncadeada<Integer>(58), listaEnc.sucessor(122));
		assertEquals(new NodoListaEncadeada<Integer>(11), listaEnc.sucessor(58));
		assertEquals(new NodoListaEncadeada<Integer>(78), listaEnc.sucessor(11));
		assertEquals(new NodoListaEncadeada<Integer>(43), listaEnc.sucessor(78));
		assertEquals(new NodoListaEncadeada<Integer>(59), listaEnc.sucessor(43));

	}

	@Test
	public void predecessorTeste() throws ElementoNaoEncontradoException {
		listaEnc.insert(206);
		listaEnc.insert(122);
		listaEnc.insert(58);
		listaEnc.insert(11);
		listaEnc.insert(78);
		listaEnc.insert(43);
		listaEnc.insert(59);
		assertEquals(new NodoListaEncadeada<Integer>(43), listaEnc.predecessor(59));
		assertEquals(new NodoListaEncadeada<Integer>(78), listaEnc.predecessor(43));
		assertEquals(new NodoListaEncadeada<Integer>(11), listaEnc.predecessor(78));
		assertEquals(new NodoListaEncadeada<Integer>(58), listaEnc.predecessor(11));
		assertEquals(new NodoListaEncadeada<Integer>(122), listaEnc.predecessor(58));
		assertEquals(new NodoListaEncadeada<Integer>(206), listaEnc.predecessor(122));

	}

	@Test
	public void isEmptyTest() throws ElementoNaoEncontradoException, ListaVaziaException {
		assertTrue(listaEnc.isEmpty());
		listaEnc.insert(206);
		listaEnc.insert(122);
		listaEnc.insert(58);
		listaEnc.insert(11);
		listaEnc.insert(78);
		listaEnc.insert(43);
		listaEnc.insert(59);
		assertFalse(listaEnc.isEmpty());
		listaEnc.remove(206);
		listaEnc.remove(122);
		listaEnc.remove(58);
		listaEnc.remove(11);
		listaEnc.remove(78);
		listaEnc.remove(43);
		assertFalse(listaEnc.isEmpty());
		listaEnc.remove(59);
		assertTrue(listaEnc.isEmpty());
	}

	@Test
	public void sizeRemoveCabecaTest() throws ElementoNaoEncontradoException, ListaVaziaException {
		assertEquals(0, listaEnc.size());
		listaEnc.insert(58);
		assertEquals(1, listaEnc.size());
		listaEnc.insert(11);
		assertEquals(2, listaEnc.size());
		listaEnc.insert(78);
		assertEquals(3, listaEnc.size());
		listaEnc.insert(43);
		assertEquals(4, listaEnc.size());
		listaEnc.remove(58);
		assertEquals(3, listaEnc.size());
		listaEnc.remove(11);
		assertEquals(2, listaEnc.size());
		listaEnc.remove(78);
		assertEquals(1, listaEnc.size());
		listaEnc.remove(43);
		assertEquals(0, listaEnc.size());
	}

	// Novos testes

	@Test
	public void removerElementoNaoExistente_DeveLancarExcecao() {
		listaEnc.insert(10);
		assertThrows(ElementoNaoEncontradoException.class, () -> {
			listaEnc.remove(20);
		});
	}

	@Test
	public void sucessorElementoNaoExistente_DeveLancarExcecao() {
		listaEnc.insert(10);
		assertThrows(ElementoNaoEncontradoException.class, () -> {
			listaEnc.sucessor(20);
		});
	}

	@Test
	public void predecessorElementoNaoExistente_DeveLancarExcecao() {
		listaEnc.insert(10);
		assertThrows(ElementoNaoEncontradoException.class, () -> {
			listaEnc.predecessor(20);
		});
	}

	@Test
	public void listaComValoresExtremos_DeveManterIntegridade() {
		listaEnc.insert(Integer.MIN_VALUE);
		listaEnc.insert(0);
		listaEnc.insert(Integer.MAX_VALUE);

		assertEquals(Integer.MIN_VALUE + ", 0, " + Integer.MAX_VALUE, listaEnc.imprimeEmOrdem());
		assertEquals(Integer.MAX_VALUE + ", 0, " + Integer.MIN_VALUE, listaEnc.imprimeInverso());
	}

	@Test
	public void inserirNull_QuandoPermitido_DeveFuncionar() {
		// Assumindo que a lista permite null
		listaEnc.insert(null);
		listaEnc.insert(10);

		assertEquals("null, 10", listaEnc.imprimeEmOrdem());
		assertNull(listaEnc.search(null));
	}

	@Test
	public void tamanhoLista_DeveSerConsistente() throws ElementoNaoEncontradoException, ListaVaziaException {
		assertEquals(0, listaEnc.size());
		listaEnc.insert(10);
		assertEquals(1, listaEnc.size());
		listaEnc.insert(20);
		assertEquals(2, listaEnc.size());
		listaEnc.remove(10);
		assertEquals(1, listaEnc.size());
	}

}