import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import root.vending.VendingMachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class VendingMachineTest {
    VendingMachine vendingMachine;

    @BeforeEach
    void init() {
        vendingMachine = new VendingMachine();
    }

    @Test
    void test_get_number_of_products_default() {
        int res = vendingMachine.getNumberOfProduct();
        assertEquals(0, res);
    }

    @Test
    void test_balance() {
        int res = vendingMachine.getCurrentBalance();
        assertEquals(0, res);
    }

    @Test
    void test_default_mode() {
        VendingMachine.Mode mode = vendingMachine.getCurrentMode();
        assertEquals(VendingMachine.Mode.OPERATION, mode);
    }

    @Test
    void test_current_sum_operation() {
        int current_sum = vendingMachine.getCurrentSum();
        assertEquals(0, current_sum);
    }

    @Test
    void test_get_coins1_op_default() {
        int coins1 = vendingMachine.getCoins1();
        assertEquals(0, coins1);
    }

    @Test
    void test_get_coins2_op_default() {
        int coins2 = vendingMachine.getCoins2();
        assertEquals(0, coins2);
    }

    @Test
    void test_get_price() {
        int price = vendingMachine.getPrice();
        assertEquals(5, price);
    }

    @Test
    void test_fill_products_default() {
        VendingMachine.Response response = vendingMachine.fillProducts();
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, response);
    }

    @ParameterizedTest
    @CsvSource({"3, 4", "0, 0", "-10, 10"})
    void test_fill_coins_op(int c1, int c2) {
        VendingMachine.Response response = vendingMachine.fillCoins(c1, c2);
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, response);
    }

    @Test
    void test_enter_admin_mode_invalid_code() {
        VendingMachine.Response response = vendingMachine.enterAdminMode(0);
        assertEquals(VendingMachine.Response.INVALID_PARAM, response);
    }

    @Test
    void test_enter_admin_mode_valid_code() {
        VendingMachine.Response response = vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Mode mode = vendingMachine.getCurrentMode();
        assertEquals(VendingMachine.Mode.ADMINISTERING, mode);
        assertEquals(VendingMachine.Response.OK, response);
    }

    @ParameterizedTest
    @CsvSource({"3, 4", "2, 5", "10, 10", "5, 6"})
    void test_fill_get_coins_admin_valid_param(int c1, int c2) {
        vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Response response = vendingMachine.fillCoins(c1, c2);
        int coins1 = vendingMachine.getCoins1();
        int coins2 = vendingMachine.getCoins2();
        assertEquals(c1, coins1);
        assertEquals(c2, coins2);
        assertEquals(VendingMachine.Response.OK, response);
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "0, 2", "2, 0", "51, 52", "104, 3", "6, 77", "-9, 5", "1, -100"})
    void test_fill_coins_admin_invalid_param(int c1, int c2) {
        vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Response response = vendingMachine.fillCoins(c1, c2);
        assertEquals(VendingMachine.Response.INVALID_PARAM, response);
    }

    @Test
    void test_fill_products_admin() {
        vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Response response = vendingMachine.fillProducts();
        int num = vendingMachine.getNumberOfProduct();
        assertEquals(40, num);
        assertEquals(VendingMachine.Response.OK, response);
    }
    @Test
    void test_exit_admin_mode_op() {
        vendingMachine.exitAdminMode();
        VendingMachine.Mode mode = vendingMachine.getCurrentMode();
        assertEquals(VendingMachine.Mode.OPERATION, mode);
    }

    @Test
    void test_exit_admin_mode_admin() {
        vendingMachine.enterAdminMode(117345294655382L);
        vendingMachine.exitAdminMode();
        VendingMachine.Mode mode = vendingMachine.getCurrentMode();
        assertEquals(VendingMachine.Mode.OPERATION, mode);
    }

    @ParameterizedTest
    @CsvSource({"14, 6", "1, 7", "30, 50", "29, 1"})
    void test_fill_get_coins1_op(int c1, int c2) {
        vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Response response = vendingMachine.fillCoins(c1, c2);
        int coins1 = vendingMachine.getCoins1();
        assertEquals(c1, coins1);
    }

    @ParameterizedTest
    @CsvSource({"2, 20", "40, 13", "36, 49", "17, 1"})
    void test_fill_get_coins2_op(int c1, int c2) {
        vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Response response = vendingMachine.fillCoins(c1, c2);
        int coins2 = vendingMachine.getCoins2();
        assertEquals(c2, coins2);
    }

    @ParameterizedTest
    @CsvSource({"2, 20", "40, 13", "36, 49", "17, 1"})
    void test_get_sum_adm(int c1, int c2) {
        vendingMachine.enterAdminMode(117345294655382L);
        vendingMachine.fillCoins(c1, c2);
        int sum = vendingMachine.getCurrentSum();
        assertEquals(c1 * VendingMachine.coinval1 + c2 * VendingMachine.coinval2, sum);
    }

    @Test
    void test_set_prices_operating() {
        VendingMachine.Response response = vendingMachine.setPrices(0);
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, response);
    }

    @Test
    void test_set_prices_admin_invalid() {
        vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Response response = vendingMachine.setPrices(-1);
        assertEquals(VendingMachine.Response.INVALID_PARAM, response);
        response = vendingMachine.setPrices(0);
        assertEquals(VendingMachine.Response.INVALID_PARAM, response);
    }

    @Test
    void test_set_prices_admin_valid() {
        vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Response response = vendingMachine.setPrices(10);
        int price = vendingMachine.getPrice();
        assertEquals(10, price);
        assertEquals(VendingMachine.Response.OK, response);
    }

    @Test
    void test_put_coin_admin() {
        vendingMachine.enterAdminMode(117345294655382L);
        VendingMachine.Response response1 = vendingMachine.putCoin1();
        VendingMachine.Response response2 = vendingMachine.putCoin2();
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, response1);
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, response2);
    }

    @Test
    void test_put_coin1_op_max() {
        vendingMachine.enterAdminMode(117345294655382L);
        vendingMachine.fillCoins(50, 1);
        assertEquals(50, vendingMachine.getCoins1());
        vendingMachine.exitAdminMode();
        VendingMachine.Response response = vendingMachine.putCoin1();
        assertEquals(VendingMachine.Response.CANNOT_PERFORM, response);
    }

    @Test
    void test_put_coin1_op() {
        VendingMachine.Response response = vendingMachine.putCoin1();
        int balance = vendingMachine.getCurrentBalance();
        assertEquals(VendingMachine.coinval1, balance);
        assertEquals(VendingMachine.Response.OK, response);
    }

    @Test
    void test_put_coin2_op_max() {
        vendingMachine.enterAdminMode(117345294655382L);
        vendingMachine.fillCoins(1, 50);
        assertEquals(50, vendingMachine.getCoins2());
        vendingMachine.exitAdminMode();
        VendingMachine.Response response = vendingMachine.putCoin2();
        assertEquals(VendingMachine.Response.CANNOT_PERFORM, response);
    }

    @Test
    void test_put_coin2_op() {
        VendingMachine.Response response = vendingMachine.putCoin2();
        int balance = vendingMachine.getCurrentBalance();
        assertEquals(VendingMachine.coinval2, balance);
        assertEquals(VendingMachine.Response.OK, response);
    }

    @Test
    void test_enter_admin_mode_pos_balance_coin1() {
        vendingMachine.putCoin1();
        VendingMachine.Response response = vendingMachine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.CANNOT_PERFORM, response);
    }

    @Test
    void test_enter_admin_mode_pos_balance_coin2() {
        vendingMachine.putCoin2();
        VendingMachine.Response response = vendingMachine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.CANNOT_PERFORM, response);
    }

    @Test
    void test_return_zero_money_op() {
        VendingMachine.Response response = vendingMachine.returnMoney();
        assertEquals(VendingMachine.Response.OK, response);
    }

    @Test
    void test_return_money_op() {
        vendingMachine.putCoin1();
        vendingMachine.putCoin2();
        VendingMachine.Response response = vendingMachine.returnMoney();
        assertEquals(VendingMachine.Response.OK, response);
    }
}
