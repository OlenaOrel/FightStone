package controller;

import dto.UserDto;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class DeckControllerTest {

    private UserService userService = mock(UserService.class);
    private CardService cardService = mock(CardService.class);
    @Mock
    private User user;
    @Mock
    private UserDto userDto;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void before() {
        when(request.getSession()).thenReturn(session);
        when(userService.getUserAttributeFromSession(request.getSession())).thenReturn(user);
        when(userService.getUserDtoAttributeFromSession(request.getSession())).thenReturn(userDto);
    }

    @Test
    public void deckViewTest() throws IOException {
        DeckController deckController = new DeckController(userService, cardService);
        ModelAndView result = deckController.deckView(request, response);
        verify(cardService).getAllCards();
        verify(userService).removeExistsCards(anyList(), anyList());
        assertTrue(result.getModel().containsKey("uDto"));
        assertTrue(result.getModel().containsKey("cards"));
        assertTrue(result.getModel().containsKey("userCards"));
        assertEquals(3, result.getModel().size());
        assertEquals("deck", result.getViewName());
        assertNotNull(result);
    }

    @Test
    public void deckViewNullUserDtoTest() throws IOException {
        when(userService.getUserDtoAttributeFromSession(request.getSession())).thenReturn(null);
        DeckController deckController = new DeckController(userService, cardService);
        ModelAndView result = deckController.deckView(request, response);
        assertNull(result);
        verify(response).sendRedirect("/fs/");
    }

    //
    @Test
    public void deckOperationsIdZeroTenCardsTest() throws IOException {
        when(cardService.isTenCardsInDeck(userDto.getDeck())).thenReturn(true);
        DeckController deckController = new DeckController(userService, cardService);
        deckController.deckOperations(request, response, 0);
        verify(cardService).isTenCardsInDeck(anyList());
        verify(userService).getUserAttributeFromSession(any());
        verify(cardService).updateUserDeck(any(), anyList());
        verify(userService).update(any());
        verify(response).sendRedirect("/fs/main/");
    }

    @Test
    public void deckOperationsIdZero() throws IOException {
        when(cardService.isTenCardsInDeck(userDto.getDeck())).thenReturn(false);
        DeckController deckController = new DeckController(userService, cardService);
        deckController.deckOperations(request, response, 0);
        verify(response).sendRedirect("/fs/deck/");
    }

    @Test
    public void deckOperationIdMaxIntegerValueTest() throws IOException {
        DeckController deckController = new DeckController(userService, cardService);
        deckController.deckOperations(request, response, 2147483647);
        verify(userService).getUserAttributeFromSession(request.getSession());
        verify(cardService).getUserCards(user.getDeck());
        verify(response).sendRedirect("/fs/main/");
    }

    //
    @Test
    public void deckOperationsLessZero() throws IOException {
        DeckController deckController = new DeckController(userService, cardService);
        deckController.deckOperations(request, response, -2);
        verify(cardService).removeCardFromUserDeck(userDto, 2);
        verify(response).sendRedirect("/fs/deck/");
    }

    @Test
    public void deckOperationsMoreZero() throws IOException {
        DeckController deckController = new DeckController(userService, cardService);
        deckController.deckOperations(request, response, 2);
        verify(cardService).addCardToUserDeck(userDto, 2);
        verify(response).sendRedirect("/fs/deck/");
    }

    @Test
    public void deckOperationsMoreTenCards() throws IOException {
        when(cardService.isTenCardsInDeck(userDto.getDeck())).thenReturn(true);
        DeckController deckController = new DeckController(userService, cardService);
        deckController.deckOperations(request, response, 2);
        verify(response).sendRedirect("/fs/deck/");
    }

    @Test
    public void deckOperationsNullUserDto() throws IOException {
        when(userService.getUserDtoAttributeFromSession(request.getSession())).thenReturn(null);
        DeckController deckController = new DeckController(userService, cardService);
        deckController.deckOperations(request, response, 5);
        verify(response).sendRedirect("/fs/");
    }
}
