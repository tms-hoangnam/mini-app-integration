# Tài liệu Tích hợp LINE Mini App (LIFF) & Zalo Mini App

## Giới thiệu

Repository này chứa tài liệu hướng dẫn chi tiết về cách tích hợp các tính năng cốt lõi của **LINE Mini App (sử dụng LIFF)** và **Zalo Mini App** vào ứng dụng của bạn. Mục tiêu là cung cấp một nguồn tài liệu tập trung, dễ hiểu, đặc biệt hữu ích cho các nhà phát triển mới tiếp cận với các nền tảng này.

Tài liệu được cấu trúc theo từng chức năng riêng biệt, coi mỗi chức năng như một "service" độc lập để dễ dàng tra cứu và triển khai.

## Đối tượng

Tài liệu này dành cho các nhà phát triển phần mềm, đặc biệt là những người mới bắt đầu tìm hiểu và tích hợp các tính năng của LINE và Zalo Mini App vào hệ thống backend hoặc frontend của họ.

## Các Chức năng được Đề cập

Tài liệu bao gồm hướng dẫn chi tiết cho các chức năng sau:

### 1. LINE Mini App (LIFF)

Mỗi chức năng được mô tả trong một file riêng tại thư mục `docs/line/`:

*   **[Push Message](./docs/line/push-message.md):** Gửi tin nhắn chủ động tới người dùng đã kết bạn với Official Account (OA) của bạn thông qua User ID.
    *   *Input:* User ID, nội dung tin nhắn (text, template, flex, etc.).
    *   *Output:* Trạng thái gửi tin nhắn thành công hoặc lỗi.
*   **[Notification (Gửi qua Messaging API)](./docs/line/notification.md):** Gửi thông báo từ server đến người dùng. *Lưu ý: LINE có cơ chế "notification" nhưng Channel Message API phải được Line Cấp quyển sử dụng API thông báo. Và sẽ có giới hạn riêng cho tài khoản đó.*
    *   *Input:* User Phone number, nội dung thông báo.
    *   *Output:* Trạng thái gửi thành công hoặc lỗi.
*   **[Reply Message](./docs/line/reply-message.md):** Phản hồi tin nhắn của người dùng trong một khoảng thời gian ngắn sau khi người dùng tương tác (ví dụ: nhắn tin cho OA, follow OA).
    *   *Input:* Reply Token (nhận từ webhook), nội dung tin nhắn phản hồi.
    *   *Output:* Trạng thái gửi phản hồi thành công hoặc lỗi.
*   **[Multicast Message](./docs/line/multicast.md):** Gửi cùng một nội dung tin nhắn đến một danh sách người dùng cụ thể (tối đa 500 người/lần với API v2).
    *   *Input:* Danh sách User ID (tối đa 500), nội dung tin nhắn.
    *   *Output:* Trạng thái gửi tin nhắn thành công hoặc lỗi.
*   **[Broadcast Message](./docs/line/broadcast.md):** Gửi tin nhắn đến tất cả người dùng đã kết bạn với OA (hoặc một tập đối tượng được lọc). Thường có giới hạn về số lượng tin nhắn miễn phí.
    *   *Input:* Nội dung tin nhắn, (tùy chọn) bộ lọc đối tượng.
    *   *Output:* Trạng thái gửi broadcast thành công hoặc lỗi.
*   **[LIFF Redirect (Deep Link)](./docs/line/liff-redirect.md):** Tạo và sử dụng URL LIFF để mở Mini App/trang web trong LINE, có thể kèm theo trạng thái hoặc dẫn đến một màn hình cụ thể (deep link).
    *   *Input:* URL của ứng dụng LIFF, (tùy chọn) `liff.state` để truyền dữ liệu.
    *   *Output:* URL LIFF (`line://app/...`).

### 2. Zalo Mini App

Mỗi chức năng được mô tả trong một file riêng tại thư mục `docs/zalo/`:

*   **[Gửi tin nhắn (OA -> User)](./docs/zalo/send-message.md):** Gửi tin nhắn từ Official Account (OA) đến người dùng đã quan tâm OA hoặc đã tương tác trong vòng 7 ngày (qua API Tương tác) hoặc gửi tin nhắn Tư vấn (qua API ZNS Template).
    *   *Input:* User ID (lấy qua `get Zalo User ID` API hoặc OAuth), nội dung tin nhắn (text, template), hoặc Template ID và params (cho ZNS).
    *   *Output:* Trạng thái gửi tin nhắn thành công hoặc lỗi.
*   **[Deep link Mini App](./docs/zalo/deep-link.md):** Tạo link để mở Zalo Mini App và điều hướng đến một trang/trạng thái cụ thể bên trong Mini App.
    *   *Input:* App ID của Mini App, path đích, (tùy chọn) query parameters.
    *   *Output:* URL deep link (`zalo://zapps/...`).

### 3. Chức năng Chung

Các chức năng áp dụng cho cả hai nền tảng hoặc là cơ chế nền tảng, được mô tả trong thư mục `docs/common/`:

*   **[Xử lý Webhook](./docs/common/webhook.md):** Tiếp nhận và xử lý các sự kiện (events) được gửi từ server của LINE/Zalo đến endpoint của bạn (ví dụ: người dùng gửi tin nhắn, follow OA, block OA).
    *   *Input:* Request HTTP POST từ LINE/Zalo chứa dữ liệu sự kiện (JSON).
    *   *Output:* Response HTTP (thường là 200 OK) để xác nhận đã nhận. Logic xử lý sự kiện phía server.
*   **[Lấy và Lưu thông tin người dùng (Profile)](./docs/common/user-profile.md):** Lấy thông tin cơ bản của người dùng (User ID, tên hiển thị, ảnh đại diện) thông qua API của LINE/Zalo sau khi người dùng cấp quyền (qua LINE Login hoặc Zalo Login/OAuth).
    *   *Input:* Access Token của người dùng.
    *   *Output:* Thông tin profile người dùng (JSON). Hướng dẫn lưu trữ thông tin này vào cơ sở dữ liệu của bạn.
