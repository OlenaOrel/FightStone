package controller;

import dto.UserDto;
import entity.Card;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/deck")
public class DeckController {
    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public DeckController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping
    public ModelAndView deckView(HttpServletRequest req,
                                 HttpServletResponse resp) throws IOException {
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
        if (uDto != null) {
            List<Card> allCards = cardService.getAllCards();
            ModelAndView out = new ModelAndView("deck");
            List<Card> userCards = uDto.getDeck();
            userService.removeExistsCards(allCards, userCards);
            out.addObject("uDto", uDto);
            out.addObject("cards", allCards);
            out.addObject("userCards", userCards);
            return out;
        } else {
            resp.sendRedirect("/fs/");
            return null;
        }
    }

    @PostMapping
    public void deckOperations(HttpServletRequest req,
                               HttpServletResponse resp,
                               @RequestParam Integer id) throws IOException {
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
        if (uDto != null) {
            if (id == 0) {
                if (cardService.isTenCardsInDeck(uDto.getDeck())) {
                    User u = userService.getUserAttributeFromSession(req.getSession());
                    if (u != null) {
                        cardService.updateUserDeck(u, cardService.getCardIdsFromUserDeck(uDto.getDeck()));
                        userService.update(u);
                        resp.sendRedirect("/fs/main/");
                    } else {
                        resp.sendRedirect("/fs/");
                    }
                } else {
                    resp.sendRedirect("/fs/deck/");
                }
            }
            if (id == 2147483647) {
                User u = userService.getUserAttributeFromSession(req.getSession());
                if (u != null) {
                    uDto.setDeck(cardService.getUserCards(u.getDeck()));
                    resp.sendRedirect("/fs/main/");
                } else {
                    resp.sendRedirect("/fs/");
                }
            }
            if (id < 0) {
                cardService.removeCardFromUserDeck(uDto, id * -1);
                resp.sendRedirect("/fs/deck/");
            }
            if (id > 0 && id < 2147483647) {
                if (!cardService.isTenCardsInDeck(uDto.getDeck())) {
                    cardService.addCardToUserDeck(uDto, id);
                    resp.sendRedirect("/fs/deck/");
                } else {
                    resp.sendRedirect("/fs/deck/");
                }
            }
        } else {
            resp.sendRedirect("/fs/");
        }
    }
}
