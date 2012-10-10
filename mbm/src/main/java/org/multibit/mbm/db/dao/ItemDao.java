package org.multibit.mbm.db.dao;

import com.google.common.base.Optional;
import org.multibit.mbm.api.response.ItemPagedQueryResponse;
import org.multibit.mbm.db.dto.Item;
import org.multibit.mbm.db.dto.User;

import java.util.List;

public interface ItemDao {

  /**
   * Attempt to locate the item using it's ID
   *
   * @param id The item ID
   * @return A matching Item (never null)
   * @throws ItemNotFoundException If the item is not matched
   */
  Optional<Item> getById(Long id) throws ItemNotFoundException;

  /**
   * Attempt to locate the item using it's SKU
   *
   * @param sku The item SKU
   * @return A matching Item
   */
  Optional<Item> getBySKU(String sku);

  /**
   * Attempt to locate the item using it's GTIN
   *
   * @param gtin The item GTIN
   * @return A matching Item
   */
  Optional<Item> getByGTIN(String gtin);

  /**
   * TODO Refactor to work like UserDao
   *
   * @param itemPagedQueryResponse The query parameters
   * @return The matching items, or an empty list (never null)
   */
  @Deprecated
  List<Item> getPagedItems(ItemPagedQueryResponse itemPagedQueryResponse);

  /**
   * Provide a paged list of all Items
   *
   * @param pageSize   The total record in one page
   * @param pageNumber The page number starts from 0
   * @return The matching items, or an empty list (never null)
   */
  List<Item> getAllByPage(final int pageSize, final int pageNumber);

  /**
   * Persist the given Item
   *
   * @param item A Item (either new or updated)
   * @return The persisted Item
   */
  Item saveOrUpdate(Item item);

  /**
   * <p>Force an immediate in-transaction flush</p>
   * <p>Normally, this is only used in test code but must be on the interface to ensure
   * that injection works as expected</p>
   */
  void flush();

}